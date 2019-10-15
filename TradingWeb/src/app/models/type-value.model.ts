export enum TypeIDEnum {
  tradeType = 1,
  exchangeType = 2,
  expenseCategory = 3,
  expenseTag = 4,
  expenseFundSource = 5
}

export interface TypeModel {
  typeId: number,
  typeName: string
}

export interface TypeValueModel {
  typeValueId: number,
  typeValueName: string,
  typeId?: number,
  parentTypeValueId?: number
}