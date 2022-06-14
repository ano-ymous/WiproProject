import { Directive } from '@angular/core';
import {AbstractControl, NG_VALIDATORS, ValidationErrors, Validator} from "@angular/forms";

@Directive({
  selector: '[appCountryValidator]',
  providers:[{provide:NG_VALIDATORS,useExisting:CountryValidatorDirective,multi:true}]
})
export class CountryValidatorDirective implements Validator{
  private countryArr:string[]=["ae","ar","at","au","be",'bg','br','ca',
    'ch','cn','co','cu','cz','de','eg','fr','gb','gr','hk','hu',
    'id','ie','il','in','it','jp','kr','lt','lv',
    'ma','mx','my','ng','nl','no','nz','ph','pl',
    'pt','ro','rs','ru','sa','se','sg','si','sk',
    'th','tr','tw','ua','us','ve','za' ]
  constructor() { }

  validate(control: AbstractControl): ValidationErrors | null {
    if(!this.countryArr.includes(control.value))
      return { validCountry: true, message: "india should be like in"};
    return null;
  }


}
