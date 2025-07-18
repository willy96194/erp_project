package com.example.erp.dto;

import java.time.LocalDate;
import java.util.List;

public class JournalEntryRequest {
    private LocalDate entryDate;
    private List<JournalDetailDTO> details;

    public LocalDate getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }


    public List<JournalDetailDTO> getDetails() {
        return details;
    }
    public void setDetails(List<JournalDetailDTO> details) {
        this.details = details;
    }
}
