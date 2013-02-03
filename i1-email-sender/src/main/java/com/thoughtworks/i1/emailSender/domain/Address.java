package com.thoughtworks.i1.emailSender.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Address {
    private String userName;
    private String userAddress;

    private Address(){
    }
    private Address(String userName, String userAddress) {
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public static Address anAddress(String userName, String userAddress) {
        return new Address(userName, userAddress);
    }

    public static Address anAddress(String userAddress) {
        return new Address(userAddress.split("@")[0], userAddress);
    }

    static InternetAddress[] toInternetAddresses(List<Address> addresses) {
        InternetAddress[] internetAddresses = new InternetAddress[addresses.size()];
        int count = 0;
        for (Address address : addresses) {
            internetAddresses[count++] = address.toInternetAddress();
        }

        return internetAddresses;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public InternetAddress toInternetAddress() {
        try {
            return new InternetAddress(this.userAddress, this.userName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
