package com.example.miniprojetandroid;

public class Students {
    String name,specialite,baccalaureat,image,email, moyenneBac,phone;

    public Students() {
    }

    public Students(String name, String phone, String specialite, String baccalaureat, String image, String email, String moyenneBac) {
        this.name = name;
        this.phone = phone;
        this.specialite = specialite;
        this.baccalaureat = baccalaureat;
        this.image = image;
        this.email = email;
        this.moyenneBac = moyenneBac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getBaccalaureat() {
        return baccalaureat;
    }

    public void setBaccalaureat(String baccalaureat) {
        this.baccalaureat = baccalaureat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoyenneBac() {
        return moyenneBac;
    }

    public void setMoyenneBac(String moyenneBac) {
        this.moyenneBac = moyenneBac;
    }
}
