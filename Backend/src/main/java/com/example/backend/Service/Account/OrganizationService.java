package com.example.backend.Service.Account;

import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Activity.Activity;

import java.util.List;

public interface OrganizationService {
    public int insertOrganization(String userName,String password,String introduction,String avator,String certification);
    public Organization getOrgByID(int ID);
    public Organization getOrgByName(String name);
    public Organization getByUserAndPassword(int ID,String password);
    public List<Activity> getAllActivityByOrg(int ID);
}
