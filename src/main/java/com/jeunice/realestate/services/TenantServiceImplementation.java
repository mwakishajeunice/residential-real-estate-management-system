package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.TenantRepository;
import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.models.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Implements the interface TenantService
public class TenantServiceImplementation{

    @Autowired
    private TenantRepository tenantRepository;

    //    The method will basically return a list of all tenants to the controller
    public List<Tenant> getAllTenants() {

        return tenantRepository.findAll();
    }

    //Save Method for Tenants
    public Tenant saveTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    //Get Tenant by phone Number
    public Optional<Tenant> getTenantByPhoneNumber(Long phoneNumber) {
        return tenantRepository.findById(phoneNumber);
    }

    //Delete method using phoneNumber
    public void deleteTenant(Long phoneNumber ) {
        tenantRepository.findById(phoneNumber).ifPresent(tenantRepository::delete);
    }
}
