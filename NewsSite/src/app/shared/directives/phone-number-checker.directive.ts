import { Directive } from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, Validator} from "@angular/forms";

@Directive({
  selector: '[appPhoneNumberChecker]',
  providers: [{provide:NG_VALIDATORS,useExisting:PhoneNumberCheckerDirective,multi:true}]
})
export class PhoneNumberCheckerDirective implements Validator{

  constructor() { }

  validate(control: AbstractControl): ValidationErrors | null {
    if(control.value==undefined || control.value.toString().length<10)
      return {phonenumberInvalid:true,message:"should 10 digits length"};
    return null;
  }

}
