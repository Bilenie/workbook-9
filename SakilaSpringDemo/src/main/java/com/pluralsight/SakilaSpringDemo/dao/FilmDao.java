package com.pluralsight.SakilaSpringDemo.dao;

import com.pluralsight.SakilaSpringDemo.models.Film;

import java.util.List;

public interface FilmDao {
    // This method will allow us to add a new Film.
    // We pass in a Film object, and the DAO will store it.
    void add(Film film);

    // This method will return a list of all Films that have been stored.
    // It returns a List<Film> — a list of Film objects.
    List<Film> getAll();

    // NOTE:
    // Right now we are only adding 2 methods here.
    // Later we could add more methods to this interface, such as:
    // - deleteById(int filmId)
    // - findById(int filmId)
    // - update(Film film)
    // These methods would give us more ways to work with Films.
}

