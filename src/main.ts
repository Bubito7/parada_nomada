import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';  // Importa platformBrowserDynamic
import { AppModule } from './app/app.module';  // Importa AppModule

platformBrowserDynamic()
  .bootstrapModule(AppModule)  // Arranca la aplicación con AppModule
  .catch((err) => console.error(err));  // Manejo de errores
