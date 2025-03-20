import { NgModule } from '@angular/core';
import { SignupComponent } from './auth/components/signup/signup.component';
import { LoginComponent } from './auth/components/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';


// Define las rutas que necesites
const routes: Routes = [
  { path: 'register', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'landing', component: LandingComponent },
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: "admin", loadChildren: () => import("./modules/admin/admin.module").then(m => m.AdminModule)},
  { path: "customer", loadChildren: () => import("./modules/customer/customer.module").then(m => m.CustomerModule)},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
