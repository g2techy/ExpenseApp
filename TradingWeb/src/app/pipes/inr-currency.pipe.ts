import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'inrCurrency'
})
export class InrCurrencyPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    
    value = Math.round(value);
    var result = value.toString().split('.');
    var lastThree = result[0].substring(result[0].length - 3);
    var otherNumbers = result[0].substring(0, result[0].length - 3);
    if (otherNumbers != '' && otherNumbers != '-')
        lastThree = ',' + lastThree;
    var output = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;

    if (result.length > 1) {
        output += "." + result[1];
    }

    return output;
  }

}
