package com.example.erp.service;

import com.example.erp.dto.JournalDetailDTO;
import com.example.erp.dto.JournalEntryRequest;
import com.example.erp.entity.Account;
import com.example.erp.entity.JournalDetail;
import com.example.erp.entity.JournalEntry;
import com.example.erp.repository.AccountRepository;
import com.example.erp.repository.JournalEntryRepository;
import com.example.erp.util.VouchernumberGenerator;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    @Autowired
    private AccountRepository  accountRepository;

    /**
     * 儲存整筆會計分錄（主表 + 明細）
     */
    @Transactional
    public void createEntryWithDetails(JournalEntryRequest request) {
        // 建立主表
        JournalEntry entry = new JournalEntry();
        entry.setEntryDate(request.getEntryDate());
        entry.setCreatedAt(LocalDateTime.now());
        
        // 自動產生傳票號碼
        LocalDate today = LocalDate.now();
        long count = journalEntryRepository.countByEntryDate(today);

        String voucherNumber = VouchernumberGenerator.generate(today,count+1);
        entry.setVoucherNumber(voucherNumber);

        // 處理明細
        List<JournalDetail> details = new ArrayList<>();
        for (JournalDetailDTO dto : request.getDetails()) {
            JournalDetail detail = new JournalDetail();

            // 找出 Account（若找不到會拋錯）
            Account account = accountRepository.findByCode(dto.getAccountCode())
                    .orElseThrow(() -> new IllegalArgumentException("找不到科目編號: " + dto.getAccountCode()));

            detail.setAccount(account);
            detail.setDebit(dto.getDebit());
            detail.setCredit(dto.getCredit());

            detail.setDescription(dto.getDescription());
            detail.setJournalEntry(entry); // 設定反向關聯

            details.add(detail);
        }
        
        entry.setDetails(details);
        journalEntryRepository.save(entry); 
    }  
    



    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry save(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }
}
