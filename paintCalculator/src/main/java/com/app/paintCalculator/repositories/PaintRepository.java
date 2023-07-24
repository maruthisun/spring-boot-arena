package com.app.paintCalculator.repositories;

import com.app.paintCalculator.entities.PaintDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintRepository extends JpaRepository<PaintDetails, Long> {
}
