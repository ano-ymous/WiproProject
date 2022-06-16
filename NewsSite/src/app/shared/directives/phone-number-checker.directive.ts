import { Directive } from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, Validator} from "@angular/forms";

@Directive({
  selector: '[appPhoneNumberChecker]',
  providers: [{provide:NG_VALIDATORS,useExisting:PhoneNumberCheckerDirective,multi:true}]
})
export class PhoneNumberCheckerDirective implements Validator{

  constructor() { }

  validate(control: AbstractControl): ValidationErrors | null {
    if(control.value==undefined || control.value > 10000000000 || control.value < 999999999)
      return { phonenumberInvalid:true,message:"should be 10 digits length" };
    return null;
  }

}
