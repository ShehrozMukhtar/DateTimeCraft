package com.example.datetime.adapter.models

fun getZonesList():List<Zone>{
     return listOf(
          Zone(
               "Asia/karachi",
               "PKT"
          ),
          Zone(
               "America/New_York",
               "EST"
          ),
          Zone(
               "America/Los_Angeles",
               "PST"
          ),
          Zone(
               "Europe/London",
               "GMT"
          ),
          Zone(
               "Australia/Sydney",
               "AEST"
          ),
          Zone(
               "Europe/Berlin",
               "CET"
          ),
          Zone(
               "Asia/Tokyo",
               "JST"
          ),
          Zone(
               "Africa/Nairobi",
               "EAT"
          ),
          Zone(
               "Asia/Dubai",
               "GST"
          ),
          Zone(
               "Europe/Moscow",
               "MSK"
          )
     )
}