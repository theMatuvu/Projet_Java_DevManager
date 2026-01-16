package com.efrei.devmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProgrammeurJpa extends JpaRepository<Programmeur, Integer> {


}
