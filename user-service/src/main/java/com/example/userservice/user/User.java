package com.example.userservice.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50, updatable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false, length = 50)
    @Size(min=2, max=50, message="Name should have size range 2-50 characters")
    private String name;

    @Column
    private String address;

    @Column(nullable = false, columnDefinition = "INT unsigned")
    private Integer salary;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "register_date",
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",
            insertable = false, updatable = false)
    private Date registerDate;

    enum MemberType {
        SILVER,
        GOLD,
        PLATINUM
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "member_type",
            columnDefinition="TINYINT unsigned COMMENT '0 for Silver, 1 for Gold, 2 for Platinum'")
    private MemberType memberType;

    @Column(name = "reference_code", updatable = false)
    private Long referenceCode;

    public User() {
    }

    public User(Integer id,
                String name,
                String email,
                String address,
                Integer salary,
                String phoneNumber,
                Date registerDate,
                MemberType memberType,
                Long referenceCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.registerDate = registerDate;
        this.memberType = memberType;
        this.referenceCode = referenceCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Long getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(Long referenceCode) {
        this.referenceCode = referenceCode;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return this.hashCode() == user.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address, salary, phoneNumber, registerDate, memberType);
    }
}
