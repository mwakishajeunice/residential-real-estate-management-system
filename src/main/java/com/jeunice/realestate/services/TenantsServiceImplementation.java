package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.TenantsRepository;
import com.jeunice.realestate.models.Tenants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantsServiceImplementation {

    @Autowired
    private TenantsRepository tenantsRepository;

    //    The method will basically return a list of all tenants to the controller
    public List<Tenants> getAllTenants() {

        return tenantsRepository.findAll();
    }

    //Save Method for Tenants
    public Tenants saveTenant(Tenants tenants) {
        return tenantsRepository.save(tenants);
    }

    //Get Tenant by phone Number
    public Optional<Tenants> getTenantByPhoneNumber(Long phoneNumber) {
        return tenantsRepository.findById(phoneNumber);
    }

    //Delete method using phoneNumber
    public void deleteTenant(Long phoneNumber ) {
        tenantsRepository.findById(phoneNumber).ifPresent(tenantsRepository::delete);
    }
}
