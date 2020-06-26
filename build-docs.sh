rm -rf docs/dokka
./gradlew dokka
cd docs/dokka
mv style.css reflectr
cd reflectr
echo "\n@media (prefers-color-scheme: dark) {
    body {
        background-color: #121212 !important;
    }

    body, p, .keyword, .symbol, code {
        color: #ece3e4 !important;
    }

    .identifier {
        color: lightblue !important;
    }

    h1 {
        color: #f9f4f4 !important;
    }

    h2 {
        color: #fdfcfc !important;
    }

    h3, h4, h5, h6 {
        color: #eeeded !important;
    }

    a {
        color: #bad0da !important;
    }
}" >> style.css
