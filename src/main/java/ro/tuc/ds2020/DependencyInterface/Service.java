package ro.tuc.ds2020.DependencyInterface;

import java.util.List;

public interface Service {
    List<String> monitor(String pickUpLocation);
    int login(String username, String usertname);
}