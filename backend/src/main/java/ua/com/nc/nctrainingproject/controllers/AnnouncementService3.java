package ua.com.nc.nctrainingproject.controllers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AnnouncementService3 {

    public  List<Announcement2> method() {
        List<Announcement2> list = new ArrayList<>();
        Announcement2 announcement = new Announcement2("yy", "yy ",
        3, "yyy", 3, "yy");
        list.add(announcement);

        return list;
    }
}
