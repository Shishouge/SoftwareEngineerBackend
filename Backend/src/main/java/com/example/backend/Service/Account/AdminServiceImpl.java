package com.example.backend.Service.Account;

import com.example.backend.DAO.Account.AdminMapper;
import com.example.backend.Entity.Account.Admin;
import com.example.backend.Entity.Account.Application;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Discuss.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(int ID, String password)
    {
        return adminMapper.getByIDandPassword(ID, password);
    }

    @Override
    public List<Report> getReports()
    {
        return adminMapper.getReports();
    }

    @Override
    public int updateIUserStatus(String ID,int flag)
    {
        int update= adminMapper.updateIUserStatus(ID, flag);
        return update;
    }

    @Override
    public List<Organization> getApplications()
    {
        return  adminMapper.getApplications();
    }

    @Override
    public int updateOUserStatus(int ID,int flag)
    {
        int update= adminMapper.updateOUserStatus(ID, flag);
        return update;
    }

    @Override
    public String getEmailByOID(int ID)
    {
        String result=adminMapper.getEmailByOID(ID);
        return result;
    }

    @Override
    public int deleteReport(String wID,String rID)
    {
        return adminMapper.deleteReport(wID,rID);
    }

}
