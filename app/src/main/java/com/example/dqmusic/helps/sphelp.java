package com.example.dqmusic.helps;

public class sphelp {
    private  static sphelp intan;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private sphelp()
    {
    }
    public static sphelp getInstance ()
    {
        if(intan==null)
        {
            synchronized (sphelp.class)
            {
                if(intan==null)
                {
                    intan=new sphelp();
                }
            }
        }
        return intan;
    }

}
