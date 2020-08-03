package com.uniandes.keymanager.repository;

import com.uniandes.keymanager.model.KeyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cuent
 */
@Repository
public interface KeyFileRepository extends JpaRepository<KeyFile, String> {

}
