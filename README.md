typesafe-config-workshop
========================

Oppgaver til faggruppemøte den 17.09.13

Før møtet
----------

1. **Installer SBT 0.13**
    
        brew install sbt
    
   eller

        brew update
        brew upgrade sbt

2. **Installer scala plugin til Eclipse eller Intellij**

   **[Scala IDE Eclipse](http://scala-ide.org/)**

   **IntelliJ plugin**

       Preferences -> plugins -> Install JetBrains plugins… -> Scala

3. **Last ned kildekoden**
       
       git clone git@github.com:froden/typesafe-config-workshop.git

4. **Sett opp prosjektet**

       cd typesafe-config-workshop
       sbt update

   Eclipse
   
       sbt eclipse
   
   IntelliJ
   
       sbt gen-idea

5. **Få testene grønne!**
   
       scalaogjvm.SettingsTest
   
   * Kommenter inn utkommentert kode i testene
   * Ikke endre noe i testene
   * **Får det til å kompilere og testene grønne**
   
Lykke til!
----------