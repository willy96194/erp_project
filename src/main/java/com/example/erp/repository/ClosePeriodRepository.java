package com.example.erp.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.erp.entity.JournalDetail;

@Repository
public interface ClosePeriodRepository extends JpaRepository<JournalDetail, Long>{
	@Query("SELECT MAX(e.entryDate) FROM JournalDetail d " +
		       "JOIN d.journalEntry e " +
		       "WHERE d.account.id = 65")
		LocalDate findLatestClosingTime();

}
