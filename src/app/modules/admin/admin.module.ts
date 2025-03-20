import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { NgZorroImportsModule } from '../../NgZorrolmportsModule';
import { PlazaComponent } from './components/plaza/plaza.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { UpdatePlazaComponent } from './components/update-plaza/update-plaza.component';


@NgModule({
  declarations: [
    PlazaComponent,
    AdminDashboardComponent,
    UpdatePlazaComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NgZorroImportsModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AdminModule { }
