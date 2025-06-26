package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile create(Profile profile);
    Profile getProfileById(int id);
    Profile update(int id, Profile profile);
}
