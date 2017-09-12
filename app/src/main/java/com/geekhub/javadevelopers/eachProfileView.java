package com.geekhub.javadevelopers;

/**
 * Created by mo.yosiwealth on 8/22/2017.
 */

public class eachProfileView {
    private String username;
    private String repo;
    private String profileLink;
    private String photo;

    public eachProfileView(String photo, String username, String repo, String profileLink) {
        this.photo = photo;
        this.username = username;
        this.repo = repo;
        this.profileLink = profileLink;
    }

    public String getUsername() {
        return username;
    }

    public String getRepo() {
        return repo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getProfileLink() {
        return profileLink;
    }
}
