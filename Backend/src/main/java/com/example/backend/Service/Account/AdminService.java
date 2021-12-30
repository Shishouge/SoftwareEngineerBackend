package com.example.backend.Service.Account;

import com.example.backend.Entity.Account.Admin;
import com.example.backend.Entity.Account.Application;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Discuss.Report;

import java.util.List;

public interface AdminService {
    public Admin login(int ID,String password);
    public List<Report> getReports();
    public int updateIUserStatus(String ID,int flag);
    public List<Organization> getApplications();
    public int updateOUserStatus(int ID,int flag);
    public String getEmailByOID(int ID);
}
