import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'alertNullRectifier'
})
export class AlertNullRectifierPipe implements PipeTransform {

  transform(value: string | null | undefined): string {
    if(value == null || value == undefined)
      return "Unknown error occured";
    return value;
  }

}
