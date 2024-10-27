package com.example.sportsteamapp.data

import com.example.sportsteamapp.R

object TeamRepository {
    val players = listOf(
        Player(
            id = 1,
            name = "Mohamed Salah",
            imageRes = R.drawable.player_salah,
            position = "Forward",
            age = 31,
            number = 11,
            nationality = "Egypt",
            height = "175cm",
            weight = "71kg",
            preferredFoot = "Left",
            stats = PlayerStats(
                appearances = 245,
                goals = 204,
                assists = 89,
                minutesPlayed = 21450,
                passAccuracy = 82,
                tacklesWon = 156
            ),
            bio = "Mohamed Salah is an Egyptian professional footballer who plays as a forward. Known for his finishing, dribbling, and speed.",
            formerTeams = listOf("Basel", "Chelsea", "Fiorentina", "Roma")
        ),
        Player(
            id = 2,
            name = "Virgil van Dijk",
            imageRes = R.drawable.player_van_dijk,
            position = "Defender",
            age = 32,
            number = 4,
            nationality = "Netherlands",
            height = "193cm",
            weight = "92kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 189,
                goals = 17,
                assists = 12,
                minutesPlayed = 16980,
                passAccuracy = 89,
                tacklesWon = 245
            ),
            bio = "Virgil van Dijk is a Dutch professional footballer who plays as a centre-back. Known for his strength, leadership, and aerial ability.",
            formerTeams = listOf("Groningen", "Celtic", "Southampton")
        ),
        Player(
            id = 3,
            name = "Alisson Becker",
            imageRes = R.drawable.player_becker,
            position = "Goalkeeper",
            age = 31,
            number = 1,
            nationality = "Brazil",
            height = "191cm",
            weight = "91kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 178,
                goals = 1,
                assists = 3,
                minutesPlayed = 16020,
                passAccuracy = 85,
                tacklesWon = 12
            ),
            bio = "Alisson Becker is a Brazilian goalkeeper known for his exceptional shot-stopping abilities and distribution skills.",
            formerTeams = listOf("Internacional", "Roma")
        ),
        Player(
            id = 4,
            name = "Trent Alexander-Arnold",
            imageRes = R.drawable.player_alexander_arnold,
            position = "Defender",
            age = 25,
            number = 66,
            nationality = "England",
            height = "175cm",
            weight = "69kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 212,
                goals = 15,
                assists = 72,
                minutesPlayed = 18360,
                passAccuracy = 87,
                tacklesWon = 189
            ),
            bio = "Trent Alexander-Arnold is an English right-back known for his exceptional passing range and attacking contributions.",
            formerTeams = listOf("Liverpool Academy")
        ),
        Player(
            id = 5,
            name = "Darwin Núñez",
            imageRes = R.drawable.player_nunez,
            position = "Forward",
            age = 24,
            number = 9,
            nationality = "Uruguay",
            height = "187cm",
            weight = "81kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 89,
                goals = 42,
                assists = 12,
                minutesPlayed = 6890,
                passAccuracy = 75,
                tacklesWon = 45
            ),
            bio = "Darwin Núñez is a Uruguayan striker known for his pace, power, and direct playing style.",
            formerTeams = listOf("Peñarol", "Almería", "Benfica")
        ),
        Player(
            id = 6,
            name = "Dominik Szoboszlai",
            imageRes = R.drawable.player_szoboszlai,
            position = "Midfielder",
            age = 23,
            number = 8,
            nationality = "Hungary",
            height = "186cm",
            weight = "74kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 65,
                goals = 12,
                assists = 18,
                minutesPlayed = 5400,
                passAccuracy = 86,
                tacklesWon = 78
            ),
            bio = "Dominik Szoboszlai is a Hungarian midfielder known for his technical ability and powerful shooting.",
            formerTeams = listOf("Red Bull Salzburg", "RB Leipzig")
        ),
        Player(
            id = 7,
            name = "Andrew Robertson",
            imageRes = R.drawable.player_robertson,
            position = "Defender",
            age = 29,
            number = 26,
            nationality = "Scotland",
            height = "178cm",
            weight = "64kg",
            preferredFoot = "Left",
            stats = PlayerStats(
                appearances = 224,
                goals = 8,
                assists = 63,
                minutesPlayed = 19800,
                passAccuracy = 83,
                tacklesWon = 198
            ),
            bio = "Andrew Robertson is a Scottish left-back known for his energy, crossing ability, and consistent performances.",
            formerTeams = listOf("Queen's Park", "Dundee United", "Hull City")
        ),
        Player(
            id = 8,
            name = "Luis Díaz",
            imageRes = R.drawable.player_diaz,
            position = "Forward",
            age = 27,
            number = 7,
            nationality = "Colombia",
            height = "178cm",
            weight = "65kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 78,
                goals = 22,
                assists = 14,
                minutesPlayed = 6120,
                passAccuracy = 81,
                tacklesWon = 67
            ),
            bio = "Luis Díaz is a Colombian winger known for his dribbling skills, creativity, and work rate.",
            formerTeams = listOf("Junior FC", "Porto")
        ),
        Player(
            id = 9,
            name = "Wataru Endo",
            imageRes = R.drawable.player_endo,
            position = "Midfielder",
            age = 30,
            number = 3,
            nationality = "Japan",
            height = "178cm",
            weight = "75kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 45,
                goals = 3,
                assists = 5,
                minutesPlayed = 3780,
                passAccuracy = 88,
                tacklesWon = 156
            ),
            bio = "Wataru Endo is a Japanese midfielder known for his defensive abilities and tactical intelligence.",
            formerTeams = listOf("Urawa Red Diamonds", "Sint-Truiden", "Stuttgart")
        ),
        Player(
            id = 10,
            name = "Cody Gakpo",
            imageRes = R.drawable.player_gakpo,
            position = "Forward",
            age = 24,
            number = 18,
            nationality = "Netherlands",
            height = "189cm",
            weight = "76kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 67,
                goals = 24,
                assists = 16,
                minutesPlayed = 5340,
                passAccuracy = 79,
                tacklesWon = 45
            ),
            bio = "Cody Gakpo is a Dutch forward known for his versatility, technical skills, and finishing ability.",
            formerTeams = listOf("PSV Eindhoven")
        ),
        Player(
            id = 11,
            name = "Alexis Mac Allister",
            imageRes = R.drawable.player_mac_allister,
            position = "Midfielder",
            age = 25,
            number = 10,
            nationality = "Argentina",
            height = "174cm",
            weight = "72kg",
            preferredFoot = "Right",
            stats = PlayerStats(
                appearances = 89,
                goals = 18,
                assists = 12,
                minutesPlayed = 7650,
                passAccuracy = 87,
                tacklesWon = 134
            ),
            bio = "Alexis Mac Allister is an Argentine midfielder known for his passing range, vision, and ability to control the tempo of the game.",
            formerTeams = listOf("Argentinos Juniors", "Boca Juniors", "Brighton")
        )
    )

    val achievements = listOf(
        Achievement(
            id = 1,
            year = 2023,
            title = "Premier League Champions",
            description = "Won the Premier League with a record-breaking 99 points.",
            imageRes = R.drawable.trophy_premier_league,
            category = "League Title"
        ),
        Achievement(
            id = 2,
            year = 2022,
            title = "FA Cup Winners",
            description = "Secured the FA Cup after a thrilling penalty shootout victory.",
            imageRes = R.drawable.trophy_fa_cup,
            category = "Cup Victory"
        ),
        Achievement(
            id = 3,
            year = 2022,
            title = "League Cup Winners",
            description = "Claimed the League Cup with a dominant performance throughout the tournament.",
            imageRes = R.drawable.trophy_league_cup,
            category = "Cup Victory"
        ),
        Achievement(
            id = 4,
            year = 2019,
            title = "UEFA Champions League Winners",
            description = "Secured the club's sixth European Cup/Champions League trophy.",
            imageRes = R.drawable.trophy_uefa,
            category = "European Trophy"
        )
    )
}