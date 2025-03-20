import { NgModule } from "@angular/core";

import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzTimePickerModule } from 'ng-zorro-antd/time-picker';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzCardModule } from 'ng-zorro-antd/card'; 
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { HttpClientModule } from '@angular/common/http';

@NgModule ({
  exports: [

        NzSpinModule,
        NzButtonModule,
        NzInputModule,
        NzFormModule,
        NzLayoutModule,
        NzSelectModule,
        NzTimePickerModule,
        NzDatePickerModule,
        NzCardModule,
        NzCheckboxModule,
        HttpClientModule,



  ]
})

export class NgZorroImportsModule {}
