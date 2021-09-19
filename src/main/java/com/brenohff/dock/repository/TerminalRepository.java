package com.brenohff.dock.repository;

import com.brenohff.dock.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalEntity, Integer> {

    Optional<TerminalEntity> getTerminalEntityByLogic(int logic);

}
