package com.example.backend.Service.Account;

import com.example.backend.DAO.Account.OrganizationMapper;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationMapper organizationMapper;
    @Override
    public int insertOrganization(String userName,String password,String introduction,String avator,String certification)
    {
        int status=0;
        return organizationMapper.insertOrganization(userName,password,introduction,avator,certification,status);
    }

    @Override
    public Organization getOrgByID(int ID)
    {
        Organization organization=organizationMapper.getOrgByID(ID);
        return organization;
    }

    @Override
    public Organization getByUserAndPassword(int ID,String password)
    {
        Organization organization=organizationMapper.getByUserAndPassword(ID, password);
        return organization;
    }

    @Override
    public Organization getOrgByName(String name)
    {
        Organization organization=organizationMapper.getOrgByName(name);
        return organization;
    }

    @Override
    public List<Activity> getAllActivityByOrg(int ID)
    {
        return organizationMapper.getActivityByOrgID(ID);
    }

}
