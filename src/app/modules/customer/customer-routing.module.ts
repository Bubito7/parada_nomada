import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { BookPlazaComponent } from './components/book-plaza/book-plaza.component';
import { MyBookingsComponent } from './components/my-bookings/my-bookings.component';

const routes: Routes = [
  { path: "dashboard", component: CustomerDashboardComponent },
  { path: "book/:id", component: BookPlazaComponent },
  { path: "my_bookings", component: MyBookingsComponent }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
