import {Directive, Input} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, Validator} from "@angular/forms";

@Directive({
  selector: '[appEmailChecker]',
  providers:[{provide:NG_VALIDATORS,useExisting:EmailCheckerDirective,multi:true}]
})
export class EmailCheckerDirective implements Validator{
  @Input('newEmail') newEmail!:string | undefined;
  constructor() { }

  validate(control: AbstractControl): ValidationErrors | null {
    if(this.newEmail == undefined || control.value!==this.newEmail){
      return {confirmEmailMissmatched: true,message:"email should match"};
    }
    return null;
  }

}
