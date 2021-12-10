package com.example.backend.Service.Account;

import com.example.backend.DAO.Account.OrganizationMapper;
import com.example.backend.Entity.Account.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Organization getByUserAndPassword(String userName,String password)
    {
        Organization organization=organizationMapper.getByUserAndPassword(userName, password);
        return organization;
    }

    @Override
    public Organization getOrgByName(String name)
    {
        Organization organization=organizationMapper.getOrgByName(name);
        return organization;
    }

}
