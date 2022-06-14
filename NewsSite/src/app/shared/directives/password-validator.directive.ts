import {Directive, Input} from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, Validator} from "@angular/forms";

@Directive({
  selector: '[appPasswordValidator]',
  providers:[{provide:NG_VALIDATORS,useExisting:PasswordValidatorDirective,multi:true}]
})
export class PasswordValidatorDirective implements Validator{
  @Input('password') prevPassword!:string|undefined;
  constructor() { }
  validate(control: AbstractControl): ValidationErrors | null {
    console.log(this.prevPassword)
    if(this.prevPassword==undefined || control.value!=this.prevPassword){
      return { passwordMismatch:true,message:"must match with password" };
    }
    return null;
  }
}
