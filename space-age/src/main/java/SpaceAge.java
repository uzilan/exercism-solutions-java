class SpaceAge {

   private double seconds;
   private static final double EARTH_ORBITAL_PERIOD = 31557600; // in seconds

   private enum OrbitalPeriod { // in earth years
      mercury(0.2408467),
      venus(0.61519726),
      mars(1.8808158),
      jupiter(11.862615),
      saturn(29.447498),
      uranus(84.016846),
      neptune(164.79132);

      double value;

      OrbitalPeriod(double value) {this.value = value;}
   }

   SpaceAge(double seconds) {
      this.seconds = seconds;
   }

   private double inEarthYears(OrbitalPeriod orbitalPeriod) {
      return seconds / orbitalPeriod.value / EARTH_ORBITAL_PERIOD;
   }

   double onEarth() {
      return seconds / EARTH_ORBITAL_PERIOD;
   }

   double onMercury() {
      return inEarthYears(OrbitalPeriod.mercury);
   }

   double onVenus() {
      return inEarthYears(OrbitalPeriod.venus);
   }

   double onMars() {
      return inEarthYears(OrbitalPeriod.mars);
   }

   double onJupiter() {
      return inEarthYears(OrbitalPeriod.jupiter);
   }

   double onSaturn() {
      return inEarthYears(OrbitalPeriod.saturn);
   }

   double onUranus() {
      return inEarthYears(OrbitalPeriod.uranus);
   }

   double onNeptune() {
      return inEarthYears(OrbitalPeriod.neptune);
   }
}
