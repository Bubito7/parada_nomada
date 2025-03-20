import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Importar componentes
import { SignupComponent } from './auth/components/signup/signup.component';
import { LoginComponent } from './auth/components/login/login.component';

// Importar módulos necesarios de NG-ZORRO y formularios
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgZorroImportsModule } from './NgZorrolmportsModule';
import { BookPlazaComponent } from './modules/customer/components/book-plaza/book-plaza.component';
import { MyBookingsComponent } from './modules/customer/components/my-bookings/my-bookings.component';
import { GetBookingsComponent } from './modules/admin/components/get-bookings/get-bookings.component';
import { SearchPlazaComponent } from './modules/admin/components/search-plaza/search-plaza.component';
import { LandingComponent } from './landing/landing.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,  // Asegúrate de que los componentes estén declarados aquí
    LoginComponent,
    BookPlazaComponent,
    MyBookingsComponent,
    GetBookingsComponent,
    SearchPlazaComponent,
    LandingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgZorroImportsModule,
    GoogleMapsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
