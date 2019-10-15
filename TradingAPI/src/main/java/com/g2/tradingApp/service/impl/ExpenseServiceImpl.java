package com.g2.tradingApp.service.impl;

import com.g2.tradingApp.dto.*;
import com.g2.tradingApp.entity.Expense;
import com.g2.tradingApp.entity.ExpenseTag;
import com.g2.tradingApp.exception.BusinessException;
import com.g2.tradingApp.exception.DataNotFoundException;
import com.g2.tradingApp.repository.ExpenseRepository;
import com.g2.tradingApp.repository.ExpenseTagRepository;
import com.g2.tradingApp.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseTagRepository expenseTagRepository;

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        LOGGER.debug("Executing method getAllExpenses");
        List<ExpenseDTO> expenses = null;
        try {
            expenses = expenseRepository.getAllExpenses();
        }catch (Exception e){
            throw new BusinessException(e);
        }
        if(expenses == null || CollectionUtils.isEmpty(expenses)){
            throw new DataNotFoundException("Expenses not found");
        }
        LOGGER.debug("Executed method getAllExpenses");
        return expenses;
    }

    @Override
    public List<ExpenseDTO> getActiveExpenses() {
        LOGGER.debug("Executing method getActiveExpenses");
        List<ExpenseDTO> expenses = null;
        try {
            expenses = expenseRepository.getActiveExpenses();
        }catch (Exception e){
            throw new BusinessException(e);
        }
        if(expenses == null || CollectionUtils.isEmpty(expenses)){
            throw new DataNotFoundException("Active expenses not found");
        }
        LOGGER.debug("Executed method getActiveExpenses");
        return expenses;
    }

    @Override
    public List<ExpenseDTO> searchExpenses(ExpenseSearchDTO expenseSearchDTO) {
        LOGGER.debug("Executing method searchExpenses");
        List<ExpenseDTO> expenses = null;
        try {
            expenses = expenseRepository.searchExpenses(expenseSearchDTO.getStartDate(), expenseSearchDTO.getEndDate());
        }catch (Exception e){
            throw new BusinessException(e);
        }
        if(expenses == null || CollectionUtils.isEmpty(expenses)){
            throw new DataNotFoundException("Expenses not found. StartDate: " + expenseSearchDTO.getStartDate() + ", EndDate: " + expenseSearchDTO.getEndDate());
        }
        LOGGER.debug("Executed method searchExpenses");
        return expenses;
    }

    @Override
    public ExpenseDetailsDTO getExpenseDetails(Long expenseId) {
        LOGGER.debug("Executing method getExpenseDetails");
        ExpenseDTO expense = null;

        try {
            expense = expenseRepository.getExpenseDetails(expenseId);
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(expense == null){
            throw new DataNotFoundException("Expense details not found for expenseId: " + expenseId);
        }

        ExpenseDetailsDTO expenseDetailsDTO = prepareExpenseDetailsDTO(expense);

        LOGGER.debug("Executed method getExpenseDetails");
        return expenseDetailsDTO;
    }

    @Override
    public Expense saveExpense(Expense expense) {
        LOGGER.debug("Executing method saveExpense");
        Expense savedExpense = null;
        try {
            savedExpense = expenseRepository.save(expense);
            if(savedExpense != null){
                List<ExpenseTag> newTags = expense.getExpenseTags();
                List<ExpenseTag> oldTags = expenseTagRepository.getExpensesByExpenseId(expense.getExpenseId());

                List<ExpenseTag> toBeAdded = new ArrayList<>();
                List<ExpenseTag> toBeDeleted = new ArrayList<>();
                if(newTags != null && !CollectionUtils.isEmpty(newTags)){
                    if(oldTags != null && !CollectionUtils.isEmpty(oldTags)){
                        toBeAdded.addAll(newTags.stream().filter(newET ->  {
                            return oldTags.stream().filter(oldET -> oldET.getTagId().equals(newET.getTagId())).count() == 0;
                        }).collect(Collectors.toList()));
                    } else {
                        toBeAdded.addAll(newTags);
                    }
                }

                if(oldTags != null && !CollectionUtils.isEmpty(oldTags)){
                    if(newTags != null && !CollectionUtils.isEmpty(newTags)){
                        toBeDeleted.addAll(oldTags.stream().filter(oldET ->  {
                            return newTags.stream().filter(newET -> oldET.getTagId().equals(newET.getTagId())).count() == 0;
                        }).collect(Collectors.toList()));
                    } else {
                        toBeDeleted.addAll(oldTags);
                    }
                }

                if(toBeDeleted != null && !CollectionUtils.isEmpty(toBeDeleted)){
                    expenseTagRepository.deleteAll(toBeDeleted);
                }
                if(toBeAdded != null && !CollectionUtils.isEmpty(toBeAdded)){
                    final Long newExpenseId = savedExpense.getExpenseId();
                    toBeAdded.forEach(et -> et.setExpenseId(newExpenseId));
                    expenseTagRepository.saveAll(toBeAdded);
                }
            }
        }catch (Exception e) {
            throw new BusinessException(e);
        }
        LOGGER.debug("Executed method saveExpense");
        return savedExpense;
    }

    @Override
    public void deleteExpense(Long expenseId) {
        LOGGER.debug("Executing method deleteExpense");
        Expense expense = findExpenseById(expenseId);
        if(expense != null){
            expense.setIsActive(false);
            expenseRepository.save(expense);
        }
        LOGGER.debug("Executed method deleteExpense");
    }

    @Override
    public List<ExpenseSummaryByTagDTO> getExpenseSummaryByTag(ExpenseSearchDTO expenseSearchDTO) {
        LOGGER.debug("Executing method getExpenseSummaryByTag");
        List<ExpenseSummaryByTagDTO> expenses = null;
        try {
            expenses = expenseRepository.getExpenseSummaryByTag(expenseSearchDTO.getStartDate(), expenseSearchDTO.getEndDate());
        }catch (Exception e){
            throw new BusinessException(e);
        }
        if(expenses == null || CollectionUtils.isEmpty(expenses)){
            throw new DataNotFoundException("Expenses not found. StartDate: " + expenseSearchDTO.getStartDate() + ", EndDate: " + expenseSearchDTO.getEndDate());
        }
        LOGGER.debug("Executed method getExpenseSummaryByTag");
        return expenses;
    }

    @Override
    public List<ExpenseDTO> searchExpensesByTag(ExpenseSearchDTO expenseSearchDTO) {
        LOGGER.debug("Executing method searchExpensesByTag");
        List<ExpenseDTO> expenses = null;
        try {
            expenses = expenseRepository.searchExpensesByTag(expenseSearchDTO.getTagId(), expenseSearchDTO.getStartDate(), expenseSearchDTO.getEndDate());
        }catch (Exception e){
            throw new BusinessException(e);
        }
        if(expenses == null || CollectionUtils.isEmpty(expenses)){
            throw new DataNotFoundException("Expenses not found. StartDate: " + expenseSearchDTO.getStartDate() + ", EndDate: " + expenseSearchDTO.getEndDate());
        }
        LOGGER.debug("Executed method searchExpensesByTag");
        return expenses;
    }

    private Expense findExpenseById(Long expenseId) {
        LOGGER.debug("Executing method findExpenseById");
        Optional<Expense> expense = null;

        try {
            expense = expenseRepository.findById(expenseId);
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(expense == null || !expense.isPresent()){
            throw new DataNotFoundException("Expense details not found for expenseId: " + expenseId);
        }

        LOGGER.debug("Executed method findExpenseById");
        return expense.get();
    }

    private ExpenseDetailsDTO prepareExpenseDetailsDTO(ExpenseDTO expenseDTO){
        LOGGER.debug("Executing method prepareExpenseDetailsDTO");
        ExpenseDetailsDTO expenseDetailsDTO = new ExpenseDetailsDTO();

        expenseDetailsDTO.setExpenseId(expenseDTO.getExpenseId());
        expenseDetailsDTO.setExpenseName(expenseDTO.getExpenseName());
        expenseDetailsDTO.setExpenseDate(expenseDTO.getExpenseDate());
        expenseDetailsDTO.setExpenseAmount(expenseDTO.getExpenseAmount());
        expenseDetailsDTO.setCategoryId(expenseDTO.getCategoryId());
        expenseDetailsDTO.setCategoryName(expenseDTO.getCategoryName());
        expenseDetailsDTO.setIsExpense(expenseDTO.getIsExpense());
        expenseDetailsDTO.setExpenseDesc(expenseDTO.getExpenseDesc());
        expenseDetailsDTO.setFundSourceId(expenseDTO.getFundSourceId());
        expenseDetailsDTO.setFundSourceName(expenseDTO.getFundSourceName());

        List<ExpenseTagDTO> expenseTags = null;
        try {
            expenseTags = getExpenseTags(expenseDTO.getExpenseId());
        } catch (DataNotFoundException e) {
            expenseTags = null;
        }
        if(expenseTags != null && !CollectionUtils.isEmpty(expenseTags)){
            //expenseDetailsDTO.setExpenseTags(prepareExpenseTagDetailsDTO(expenseTags));
            expenseDetailsDTO.setExpenseTags(expenseTags.stream().map(this::prepareExpenseTagDetailsDTO).collect(Collectors.toList()));
        }

        LOGGER.debug("Executing method prepareExpenseDetailsDTO");
        return expenseDetailsDTO;
    }

    private List<ExpenseTagDTO> getExpenseTags(Long expenseId) {
        LOGGER.debug("Executing method getExpenseTags");
        List<ExpenseTagDTO> expenseTags = null;

        try {
            expenseTags = expenseTagRepository.getExpenseTags(expenseId);
        }catch (Exception e){
            throw new BusinessException(e);
        }

        if(expenseTags == null || CollectionUtils.isEmpty(expenseTags)){
            throw new DataNotFoundException("Expense tags not found for expenseId: " + expenseId);
        }

        LOGGER.debug("Executed method getExpenseTags");
        return expenseTags;
    }

    /*
    private List<ExpenseTagDetailsDTO> prepareExpenseTagDetailsDTO(List<ExpenseTagDTO> expenseTags) {
        List<ExpenseTagDetailsDTO> tagDetailsDTOList = new ArrayList<>();
        if(expenseTags != null && !CollectionUtils.isEmpty(expenseTags)){
            expenseTags.forEach(expense -> {
                ExpenseTagDetailsDTO expenseTagDetailsDTO = new ExpenseTagDetailsDTO();
                expenseTagDetailsDTO.setExpenseTagId(expense.getExpenseTagId());
                expenseTagDetailsDTO.setExpenseId(expense.getExpenseId());
                expenseTagDetailsDTO.setTagId(expense.getTagId());
                expenseTagDetailsDTO.setTagName(expense.getTagName());
                tagDetailsDTOList.add(expenseTagDetailsDTO);
            });
        }
        return tagDetailsDTOList;
    }
    */

    private ExpenseTagDetailsDTO prepareExpenseTagDetailsDTO(ExpenseTagDTO expenseTag) {
        ExpenseTagDetailsDTO expenseTagDetailsDTO = new ExpenseTagDetailsDTO();
        if(expenseTag != null) {
            expenseTagDetailsDTO.setExpenseTagId(expenseTag.getExpenseTagId());
            expenseTagDetailsDTO.setExpenseId(expenseTag.getExpenseId());
            expenseTagDetailsDTO.setTagId(expenseTag.getTagId());
            expenseTagDetailsDTO.setTagName(expenseTag.getTagName());
        }
        return expenseTagDetailsDTO;
    }

}
