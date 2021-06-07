package com.jeunice.realestate.controllers;

import com.jeunice.realestate.dao.TenantsRepository;
import com.jeunice.realestate.models.Tenants;
import com.jeunice.realestate.services.TenantsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AuthController {
    @Autowired
    private TenantsServiceImplementation tenantsServiceImplementation;
    @Autowired
    private TenantsRepository tenantsRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //display forgot password page
    @GetMapping("/forgotPassword")
    public ModelAndView forgotPasswordPage(){
        return new ModelAndView("/forgotPassword");

    }

    //process form submission from forgot password page
    @PostMapping("/forgotPassword")
    public ModelAndView processForgotPasswordPage(ModelAndView modelAndView, @RequestParam("email") String userEmail,
                                                  HttpServletRequest request){
        //fetch user in db by email
        Optional<Tenants> optional = Optional.ofNullable(tenantsRepository.findByEmail(userEmail));

        if (optional.isPresent()){
            //generate token for reset password
            Tenants tenants = optional.get();
            tenants.setResetToken(UUID.randomUUID().toString());

            //save the token in db
            tenantsServiceImplementation.saveTenant(tenants);

            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            //Create the message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("supportcenter@gmail.com");
            passwordResetEmail.setTo(tenants.getEmail());
            passwordResetEmail.setSubject("Complete your Real Estate website request");
            passwordResetEmail.setText("Click the link to complete your password reset: " + "" + url + "/resetPasswordPage?token=" + tenants.getResetToken());

            //send the email
            emailService.sendEmail(passwordResetEmail);

            //add a sucsess message
            modelAndView.setViewName("redirect:/forgotPassword?success");
//
        }else {
//            modelAndView.addObject("errorMessage", "Email accout")
            return new ModelAndView("forgotPassword?error");

        }
        return modelAndView;
    }

    //display reset password page
    @GetMapping("/resetPasswordPage")
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token){
        Optional<Tenants> user1 = tenantService.findUserByResetToken(token);

        if (user1.isPresent()){
            modelAndView.addObject("resetToken", token);
        }else {
            modelAndView.addObject("errorMessage", "Invalid link");
        }
        modelAndView.setViewName("resetPassword");

        return modelAndView;
    }

    //process the reset form
    @PostMapping("/resetPassword")
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams,
                                       RedirectAttributes attributes){

        //find user linked to the token
        Optional<Tenants> user1 = tenantService.findUserByResetToken(requestParams.get("token"));

        if (user1.isPresent()){
            Tenants resetUser = user1.get();

            //set new password
            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

            //set the token to null so that it cannot be used again
            resetUser.setResetToken(null);

            //saving the user
            tenantService.saveUser(resetUser);

            //redireccting attributes
            attributes.addFlashAttribute("successMessage", "Successfully reset your password");

            modelAndView.setViewName("redirect:/login");
            return modelAndView;

        }else {
            modelAndView.addObject("errorMessage", "Invalid password link");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    //handling the exception of accessing the resetPassword page without token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingTokens(MissingServletRequestParameterException ex){
        return new ModelAndView("redirect:/login");
    }

}
