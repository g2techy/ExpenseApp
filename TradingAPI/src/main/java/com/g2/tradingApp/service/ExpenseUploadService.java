package com.g2.tradingApp.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public interface ExpenseUploadService {

    ByteArrayInputStream downloadTemplateFile();

    ByteArrayInputStream uploadExpenses(InputStream inputStream);

}
