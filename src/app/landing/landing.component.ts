import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  // Coordenadas de Vejer de la Frontera
  location = { lat: 36.228375, lon: -6.066145 };
  center = { lat: 36.228375, lng: -6.066145 };  // Coordenadas del centro del mapa

  zoom = 15;  // Nivel de zoom para el mapa
  label = 'Parada Nómada';  // Etiqueta del marcador en el mapa

  // Datos del clima
  weatherData: any;
  apiKey = 'c22167e0f3294e95983230556251903'; // Aquí pon tu API Key de WeatherAPI

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getWeather();
  }

  // Método para obtener los datos del clima
  getWeather() {
    const url = `https://api.weatherapi.com/v1/current.json?key=${this.apiKey}&q=${this.location.lat},${this.location.lon}&lang=es`;

    this.http.get(url).subscribe(
      (data) => {
        console.log('Datos del clima:', data);  // Para verificar si la solicitud es exitosa
        this.weatherData = data;
      },
      (error) => {
        console.error('Error obteniendo clima:', error);
      }
    );
  }
}
