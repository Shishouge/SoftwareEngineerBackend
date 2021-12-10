package com.example.backend.Service.Account;

import com.example.backend.Entity.Account.Organization;

public interface OrganizationService {
    public int insertOrganization(String userName,String password,String introduction,String avator,String certification);
    public Organization getOrgByID(int ID);
    public Organization getOrgByName(String name);
    public Organization getByUserAndPassword(String userName,String password);
}
