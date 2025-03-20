import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  plaza(plazaDto: FormData): Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/plaza", plazaDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  getAllPlazas(): Observable<any> {
    return this.http.get<any>(BASIC_URL + "/api/admin/plazas", {
      headers: this.createAuthorizationHeader()
    }).pipe(
      tap((res) => {
        //console.log('Respuesta de la API:', res);
        // Si la respuesta contiene un arreglo de plazas, por ejemplo, 'res.plazas'
        // puedes hacer algo como esto:
        // this.plazas = res.plazas;
      })
    );
  }

  deletePlaza(id:number):Observable<any> {
    return this.http.delete(BASIC_URL + "/api/admin/plaza/" + id, {
      headers: this.createAuthorizationHeader()
    });
  }

  getPlazaById(id: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/plaza/" + id, {
      headers: this.createAuthorizationHeader()
    })
  }

  updatePlaza(plazaId: number, plazaDto: any): Observable<any> {
    return this.http.put(BASIC_URL + "/api/admin/plaza/" + plazaId, plazaDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getPlazaBookings(): Observable<any> {
    return this.http.get<any>(BASIC_URL + "/api/admin/plaza/bookings", {
      headers: this.createAuthorizationHeader()
    }).pipe(
      tap((res) => {
        //console.log('Respuesta de la API:', res);
        // Si la respuesta contiene un arreglo de plazas, por ejemplo, 'res.plazas'
        // puedes hacer algo como esto:
        // this.plazas = res.plazas;
      })
    );
  }

  changeBookingStatus(bookingId: number, status: string): Observable<any> {
    let params = new HttpParams();
    params = params.set('status', status);

    console.log(params);

    return this.http.get<any>(BASIC_URL + `/api/admin/plaza/bookings/${bookingId}`, {
      headers: this.createAuthorizationHeader(),
      params
    }).pipe(
      tap((res) => {
        //console.log('Respuesta de la API:', res);
        // Si la respuesta contiene un arreglo de plazas, por ejemplo, 'res.plazas'
        // puedes hacer algo como esto:
        // this.plazas = res.plazas;
      })
    );
  }

  searchPlaza(searchPlazaDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/plaza/search", searchPlazaDto, {
      headers: this.createAuthorizationHeader()
    });
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
