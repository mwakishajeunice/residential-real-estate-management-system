package com.jeunice.realestate.dao;
import org.springframework.mail.SimpleMailMessage;

public interface EmailRepository {
        public void sendEmail(SimpleMailMessage email);

    }
