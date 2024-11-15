{ pkgs }: {
    deps = [
      pkgs.twelf
      pkgs.cliquer
      pkgs.nano
        pkgs.graalvm17-ce
        pkgs.maven
        pkgs.replitPackages.jdt-language-server
        pkgs.replitPackages.java-debug
    ];
}