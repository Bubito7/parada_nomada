import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { PlazaComponent } from './components/plaza/plaza.component';
import { UpdatePlazaComponent } from './components/update-plaza/update-plaza.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';
import { SearchPlazaComponent } from './components/search-plaza/search-plaza.component';

const routes: Routes = [
  { path: "dashboard", component: AdminDashboardComponent},
  { path: "plaza", component: PlazaComponent},
  { path: 'plaza/:id', component: UpdatePlazaComponent },
  { path: 'bookings', component: GetBookingsComponent },
  { path: 'search', component: SearchPlazaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
