import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {}

  getAllPlazas(): Observable<any> {
      return this.http.get<any>(BASIC_URL + "/api/customer/plazas", {
        headers: this.createAuthorizationHeader()
      }).pipe(
        tap((res) => {
          console.log('Respuesta de la API:', res);
          // Si la respuesta contiene un arreglo de plazas, por ejemplo, 'res.plazas'
          // puedes hacer algo como esto:
          // this.plazas = res.plazas;
        })
      );
    }

    bookAPlaza(bookAPlaza: any): Observable<any> {
      return this.http.post(BASIC_URL + "/api/customer/plaza/book", bookAPlaza, {
        headers: this.createAuthorizationHeader()
      }).pipe(
        tap((res) => {
          console.log('Reserva realizada con éxito', res);
          // Aquí revisa que las fechas estén enviándose y recibiéndose
        })
      );
    }



    getPlazaById(plazaId: number): Observable<any> {
      return this.http.get<any>(BASIC_URL + "/api/customer/plaza/" + plazaId, {
        headers: this.createAuthorizationHeader()
      }).pipe(
        tap((res) => {
          console.log('Respuesta de la API:', res);
          // Si la respuesta contiene un arreglo de plazas, por ejemplo, 'res.plazas'
          // puedes hacer algo como esto:
          // this.plazas = res.plazas;
        })
      );
    }

    getBookingsByUserId(): Observable<any> {
      return this.http.get<any>(BASIC_URL + "/api/customer/plaza/bookings/" + StorageService.getUserId(), {
        headers: this.createAuthorizationHeader()
      })
    }

    createAuthorizationHeader(): HttpHeaders {
      let authHeaders: HttpHeaders = new HttpHeaders();
      const token = StorageService.getToken();
      // Aquí se llama de forma correcta al método estático
      console.log('Token enviado:', token);
      return authHeaders.set(
        'Authorization',
        'Bearer ' + token  // Asegúrate de que el token esté correctamente enviado
      );
    }
}
