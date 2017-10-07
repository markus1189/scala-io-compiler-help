{ pkgs ? import <nixpkgs> {} }: with pkgs;

let
  hsPkgs = ps: with ps; [
    containers
    aeson
    shake
    turtle
    HaTeX
  ];
  tex = texlive.combine {
    inherit (texlive)
    animate
    babel
    beamer
    chngcntr
    cleveref
    enumitem
    etoolbox
    excludeonly
    fancyvrb
    float
    framed
    ifplatform
    lineno
    listings
    mdframed
    media9
    microtype
    minted
    needspace
    ocgx2
    pgf
    scheme-medium
    todonotes
    upquote
    xcolor
    xstring;
  };
in
stdenv.mkDerivation {
  name = "presentation";
  buildInputs = [
    tex
    which
  ] ++ (with haskellPackages; [
    (ghcWithPackages hsPkgs)
  ]) ++ (with pythonPackages; [
    pygments
  ]);
}
