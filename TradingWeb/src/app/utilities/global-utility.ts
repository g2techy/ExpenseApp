
export class Utility {

  static getDateFromDP(dp : any) : string {
    let date = '';
    if(dp !== undefined && dp != null && dp.year){
      date = dp.year + '-' + String(dp.month).padStart(2, '0') + '-' + String(dp.day).padStart(2, '0');
    }
    return date;
  }

  static getDatePickerDateFromDate(date: any) {    
    if(date){
      date = new Date(date);
      return { year: date.getFullYear(), month: (date.getMonth()+1), day: date.getDate() };
    }
    return null;
  }

  static copyObject(obj) : any {
    return (JSON.parse(JSON.stringify(obj)));
  }

  static Expense: any;
}

class ExpenseUtilty {

  static getTotalExpense(exenses): number {
    let totalAmt = 0;
    if(exenses){
      let spends = exenses.filter(e => e.isExpense);
      if(spends && spends.length > 0){
        totalAmt = spends.reduce((a, b) => a + b.expenseAmount, 0);
      }
    }
    return totalAmt;
  }

  static getExpenseCategories(expenses: any[]) {
    let retunValue = [];
    if(expenses && expenses.length > 0) {
      expenses.forEach(item => {
        let cv;
        if(retunValue.filter(rv => rv.categoryId === item.categoryId).length == 0){
          cv = {categoryId: item.categoryId, categoryName: item.categoryName, expenseAmount: 0, expenseCount: 0};
          retunValue.push(cv);
        } else {
          cv = retunValue.filter(rv => rv.categoryId == item.categoryId)[0];
        }
        if(item.isExpense){
          cv.expenseAmount += item.expenseAmount;
        }
        cv.expenseCount++;      
      });
    }
    return retunValue;
  }

  static getFundSources(expenses: any[]) {
    let retunValue = [];
    if(expenses && expenses.length > 0) {
      expenses.forEach(item => {
        let cv;
        if(retunValue.filter(rv => rv.fundSourceId === item.fundSourceId).length == 0){
          cv = {fundSourceId: item.fundSourceId, fundSourceName: item.fundSourceName, expenseAmount: 0, expenseCount: 0};
          retunValue.push(cv);
        } else {
          cv = retunValue.filter(rv => rv.fundSourceId == item.fundSourceId)[0];
        }
        if(item.isExpense){
          cv.expenseAmount += item.expenseAmount;
        }
        cv.expenseCount++;      
      });
    }
    return retunValue;
  }

}

Utility.Expense = ExpenseUtilty;