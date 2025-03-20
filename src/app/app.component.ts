import { Component, OnInit } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'parada_nomada';

  isCustomerLoggedIn: boolean = false;
  isAdminLoggedIn: boolean = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Asignar valores iniciales
    this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
    this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();

    // Escuchar cambios en la navegación
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
      }
    });
  }

  logout() {
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }

  // Función para hacer scroll a las secciones
  scrollToSection(sectionId: string) {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }
}
