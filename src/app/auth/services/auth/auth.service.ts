import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = "http://localhost:8080"; // Asegúrate de que la URL esté correctamente escrita

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // Método para el registro de usuario
  register(signupRequest: any): Observable<any> {
    return this.http.post(BASE_URL + "/api/auth/signup", signupRequest);
  }

  // Método para el login de usuario
  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + "/api/auth/login", loginRequest); 
  }
}
